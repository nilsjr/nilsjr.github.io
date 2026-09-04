const http = require('http');
const fs = require('fs');
const path = require('path');
const zlib = require('zlib');

const root = process.argv[2];
const port = Number(process.argv[3] || 8137);

const mime = {
  '.html': 'text/html; charset=utf-8',
  '.js': 'application/javascript; charset=utf-8',
  '.map': 'application/json; charset=utf-8',
  '.css': 'text/css; charset=utf-8',
  '.svg': 'image/svg+xml',
  '.png': 'image/png',
  '.gif': 'image/gif',
  '.json': 'application/json; charset=utf-8',
};

const textTypes = new Set(['.html', '.js', '.map', '.css', '.json', '.svg']);

const rootResolved = path.resolve(root);

http.createServer((req, res) => {
  let urlPath = decodeURIComponent(req.url.split('?')[0]);
  if (urlPath === '/') urlPath = '/index.html';
  const filePath = path.resolve(rootResolved, `.${urlPath}`);
  if (filePath !== rootResolved && !filePath.startsWith(rootResolved + path.sep)) {
    res.writeHead(403);
    res.end('Forbidden');
    return;
  }
  fs.readFile(filePath, (err, data) => {
    if (err) {
      res.writeHead(404);
      res.end('Not found');
      return;
    }
    const ext = path.extname(filePath);
    const headers = {
      'Content-Type': mime[ext] || 'application/octet-stream',
      'Cache-Control': 'max-age=600',
    };
    const acceptEncoding = req.headers['accept-encoding'] || '';
    if (textTypes.has(ext) && acceptEncoding.includes('gzip')) {
      zlib.gzip(data, (e, compressed) => {
        if (e) {
          res.writeHead(200, headers);
          res.end(data);
          return;
        }
        headers['Content-Encoding'] = 'gzip';
        res.writeHead(200, headers);
        res.end(compressed);
      });
    } else {
      res.writeHead(200, headers);
      res.end(data);
    }
  });
}).listen(port, () => console.log(`serving ${root} on :${port} (gzip)`));

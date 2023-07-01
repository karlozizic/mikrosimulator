const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
    app.use(
        "/spring",
        createProxyMiddleware({
            target: "http://localhost:8080/",
            changeOrigin: true,
        })
    );

    app.use(
        "/vehicles",
        createProxyMiddleware({
            target: "http://localhost:2222/",
            changeOrigin: true,
        })
    );

    app.use(
        "/payments",
        createProxyMiddleware({
            target: "http://localhost:4444/",
            changeOrigin: true,
        })
    );
};

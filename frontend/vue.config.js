module.exports = {
  css: {
    loaderOptions: {
      less: {
        javascriptEnabled: true
      }
    }
  },
  lintOnSave: false,
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8518',
        ws: true,
        changeOrigin: true
      }
    }
  },
  outputDir: 'target/dist',
  assetsDir: 'static'
};

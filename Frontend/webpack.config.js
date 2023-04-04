const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true
  },
  entry: {
    examplePage: path.resolve(__dirname, 'src', 'pages', 'examplePage.js'),
    landingPage: path.resolve(__dirname, 'src', 'pages', 'landingPage.js'),
    createAccount: path.resolve(__dirname, 'src', 'pages', 'createAccount.js'),
    buyerPage: path.resolve(__dirname, 'src', 'pages', 'buyerPage.js'),
    sellerPage: path.resolve(__dirname, 'src', 'pages', 'sellerPage.js'),
    vehiclePage: path.resolve(__dirname, 'src', 'pages', 'vehiclePage.js')
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].js',
  },
  devServer: {
    https: false,
    port: 8080,
    open: true,
    openPage: 'http://localhost:8080',
    // diableHostChecks, otherwise we get an error about headers and the page won't render
    disableHostCheck: true,
    contentBase: 'packaging_additional_published_artifacts',
    // overlay shows a full-screen overlay in the browser when there are compiler errors or warnings
    overlay: true,
    proxy:[ { context: [ '/' ], target: 'http://localhost:5001' } ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html',
      filename: 'index.html',
      inject: false,
      minify: false
    }),
    new HtmlWebpackPlugin({
      template: './src/createAccount.html',
      filename: 'createAccount.html',
      inject: false,
      minify: false
    }),
    new HtmlWebpackPlugin({
      template: './src/buyer.html',
      filename: 'buyer.html',
      inject: false,
      minify: false
    }),
    new HtmlWebpackPlugin({
      template: './src/seller.html',
      filename: 'seller.html',
      inject: false,
      minify: false
    }),
    new HtmlWebpackPlugin({
      template: './src/vehicle.html',
      filename: 'vehicle.html',
      inject: false,
      minify: false
    }),
    new CopyPlugin({
      patterns: [
        {
          from: path.resolve('src/css'),
          to: path.resolve("dist/css")
        }
      ]
    }),
    new CleanWebpackPlugin()
  ]
}

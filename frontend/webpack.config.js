const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const webpack = require('webpack');

module.exports = (process) => {
  return {
    entry: path.resolve(__dirname, 'src', 'index.tsx'),
    output: {
      path: path.resolve(__dirname, 'dist'),
      filename: 'bundle.js',
      publicPath: '/'
    },
    mode: 'development',
    module: {
      rules: [
        {
          test: /\.[jt]sx?$/,
          use: ['babel-loader'],
          exclude: /node_modules/
        },
        {
          test: /\.css$/,
          use: ['style-loader', 'css-loader']
        },
        {
          test: /\.scss$/,
          use: ['style-loader', 'css-loader', 'sass-loader']
        },
        {
          test: /\.(?:ico|gif|png|jpg|jpeg)$/i,
          type: 'asset/resource'
        },
        {
          test: /\.(woff(2)?|eot|ttf|otf|svg|)$/,
          type: 'asset/inline'
        }
      ]
    },
    resolve: {
      alias: {
        '@src': path.resolve(__dirname, 'src')
      },
      extensions: ['.tsx', '.ts', '.js', '.jsx']
    },
    plugins: [
      new HtmlWebpackPlugin({
        template: path.resolve(__dirname, './src/index.html')
      }),
      new CleanWebpackPlugin(),
      new webpack.DefinePlugin({
        SERVICE_BASE_URL: JSON.stringify(
          process.env === 'development'
            ? 'http://localhost:8080'
            : 'http://csci5308vm12.research.cs.dal.ca:8080'
        )
      })
    ],
    devServer: {
      static: path.join(__dirname, './src'),
      port: 3001,
      hot: 'only',
      compress: true,
      historyApiFallback: true, // nested routes
      open: true
    }
  };
};

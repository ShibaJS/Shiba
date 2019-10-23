const path = require("path");

module.exports = {
  entry: "./src/index.tsx",
  mode: "production",
  target: "web",
  module: {
    rules: [
      {
        exclude: /node_modules/,
        loader: "ts-loader",
        test: /\.tsx?$/,
      },
    ],
  },
  output: {
    filename: "bundle.js",
    path: path.resolve(__dirname, "build"),
  },
  resolve: {
    extensions: [".tsx", ".ts", ".js"],
  },
  performance: {
    hints: false,
  }
};

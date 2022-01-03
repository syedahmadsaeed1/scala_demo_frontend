module.exports = {
    "entry": {
      "scala_test-fastopt": ["/home/ahmad/projects/scala/scala_test/target/scala-2.12/scalajs-bundler/main/scala_test-fastopt-entrypoint.js"]
    },
    "target": "web",
    "output": {
      "path": "/home/ahmad/projects/scala/scala_test/target/scala-2.12/scalajs-bundler/main",
      "filename": "[name]-library.js",
      "library": "ScalaJSBundlerLibrary",
      "libraryTarget": "var"
    },
    "mode": "development",
    "devtool": "source-map",
    "module": {
      "rules": [{
        "test": new RegExp("\\.js$"),
        "enforce": "pre",
        "use": ["source-map-loader"]
      }]
    }
  }
(defproject virtual-dom-signals "0.1.0-SNAPSHOT"
  :description "Virtual DOM with signals"
  :url "https://github.com/anthoq88/virtual-dom-signals"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"]
                 [org.clojure/test.check "0.9.0"]
                 [cljsjs/virtual-dom "2.1.1-0"]]
  
  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-1"]]

  :source-paths ["src"]

  :aliases {"up" ["figwheel" "dev"]}

  :cljsbuild {:test-commands 
              {"unit" ["phantomjs" "phantom/unit-test.js" "resources/private/unit-test.html"]}

              :builds
              [{:id "dev" ;;TODO rename to examples
                :source-paths ["src"] ;;TODO add examples to paths
                :figwheel true
                :compiler {:main virtual-dom-signals.core
                           :asset-path "/js/compiled/out"
                           :output-to "resources/public/js/compiled/virtual-dom-signals.js"
                           :output-dir "resources/public/js/compiled/out"
                           :optimizations :none
                           :source-map-timestamp true}}
               {:id "test"
                :source-paths ["src" "test"]
                :compiler {:output-to "resources/private/compiled/js/unit-test.js"
                           :optimizations :whitespace
                           :pretty-print true}}]}
  
  :clean-targets ^{:protect false} ["resources/public/js/compiled" 
                                    "resources/private/js/compiled"
                                    :target-path]
  
  :figwheel {:css-dirs ["resources/public/css"]
             :server-port 8000})

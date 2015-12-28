(defproject vdom-signals "0.1.0-SNAPSHOT"
  :description "Virtual DOM with signals"
  :url "https://github.com/anthoq88/vdom-signals"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"]
                 [cljsjs/virtual-dom "2.1.1-0"]]
  
  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-1"]]

  :source-paths ["src"]
  
  :aliases {"up" ["figwheel" "dev"]}
  
  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :figwheel true
                :compiler {:main vdom-signals.core
                           :asset-path "/js/compiled/out"
                           :output-to "resources/public/js/compiled/vdom-signals.js"
                           :output-dir "resources/public/js/compiled/out"
                           :optimizations :none
                           :source-map-timestamp true}}]}
  
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]
  
  :figwheel {:css-dirs ["resources/public/css"]
             :server-port 8000})

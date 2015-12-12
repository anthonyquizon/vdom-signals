(ns ^:figwheel-always clojurescript-signals.core
  (:require [goog.dom :as gdom]
            [goog.events :as gevents]
            [cljs.core.async :as async :refer [>! <!]]))

(enable-console-print!)

(print "hello world!")

;; (defn animation-frames
;;   "Returns a signal of animation frames."
;;   []
;;   (let [ch (async/chan)]
;;     (animate (fn tick [t]
;;                (async/put! ch t)
;;                (animate tick)))
;;     ch))

;; (defn sample-on [sample-ch value-ch]
;;   (let [ch (async/chan)
;;         sample (async/take! sample-ch)
;;         value (async/take! value-ch)]
;;     (async/put! ch value)
;;     ch))

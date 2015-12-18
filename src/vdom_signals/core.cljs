(ns ^:figwheel-always vdom-signals.core
  (:refer-clojure :exclude [map merge])
  (:require [goog.dom :as gdom]
            [goog.events :as gevents]
            [cljs.core.async :as async :refer [>! <!]]
            [vdom-signal.dom :as dom]))

(enable-console-print!)

;; TODO transducers

(defn animation-frames
  "Returns a signal of animation frames."
  []
  (let [ch (async/chan)]
    (dom/animate (fn tick [t]
               (async/put! ch t)
               (dom/animate tick)))
    ch))

;; (defn sample-on [sample-ch value-ch]
;;   (let [ch (async/chan)
;;         sample (async/take! sample-ch)
;;         value (async/take! value-ch)]
;;     (async/put! ch value)
;;     ch))


;; print every second


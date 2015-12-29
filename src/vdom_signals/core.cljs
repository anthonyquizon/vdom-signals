(ns ^:figwheel-always vdom-signals.core
  (:refer-clojure :exclude [map merge])
  (:require [goog.dom :as gdom]
            [goog.events :as gevents]
            [cljs.core.async :as async :refer [>! <!]]
            [vdom-signals.dom :as dom])
  (:require-macros [cljs.core.async.macros :refer [go-loop]]))

(enable-console-print!)

;; macro defsig -> automatically called?
;; TODO transducers

(defn animation-frames
  "Returns a signal of animation frames."
  []
  (let [ch (async/chan)]
    (dom/animate (fn tick [t]
                   (async/put! ch t)
                   (dom/animate tick)))
    ch))

(defn sample-on [sample-ch value-ch]
  (let [ch (async/chan)]
    (go-loop 
      []
      (let [sample (<! sample-ch)
            value (<! value-ch)]
        (>! ch value)
        (recur)))
    ch))

(defn map [f signal]
  (let [ch (async/chan)] ;;TODO core async map
    (go-loop 
      []
      (let [value (<! signal)]
        (>! ch (f value)))
      (recur))
    ch))

;;render map


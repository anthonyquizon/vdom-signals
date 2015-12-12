(ns clojurescript-signals.dom
  (:require))

(defn animate
  "Call f on the next animation frame."
  [f]
  (.requestAnimationFrame js/window f))

(defn render [signal elem]
  )

(ns vdom-signals.dom
  (:require [goog.dom :as gdom]
            [goog.events :as gevents]
            [cljsjs.virtual-dom]
            [cljs.core.async :as async :refer [>! <!]])
  (:require-macros [cljs.core.async.macros :refer [go-loop]]))

(declare vnode)

(defn vchild [child]
  (if (vector? child)
    (if (keyword? (first child))
      (vnode child)
      (clj->js (map vnode child)))
    child))

(defn vnode [[nnode props child]]
  (let [child' (vchild child)
        props' (clj->js props)
        nname' (name nnode)]
    (js/virtualDom.h nname' props' child')))

(defn animate
  "Call f on the next animation frame."
  [f]
  (.requestAnimationFrame js/window f))

(defn renderer [elem]
  (let [tree (atom (js/virtualDom.VText. ""))
        root (atom (js/virtualDom.create @tree))
        update (fn [f] (.requestAnimationFrame js/window f))]
    (gdom/removeChildren elem)
    (gdom/append elem @root)
    (fn [view]
      (let [new-tree (vnode view)
            patches (js/virtualDom.diff @tree new-tree)]
        (reset! tree new-tree)
        (update #(swap! root js/virtualDom.patch patches))))))

(defn render! [dom-signal elem]
  (let [render (renderer elem)]
    (go-loop 
      []
      (render (<! dom-signal))
      (recur))))

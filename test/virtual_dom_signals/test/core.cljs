(ns ^:figwheel-always vdom-signals.test.core
  (:require [goog.dom :as gdom]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :refer-macros [defspec]]
            [cljs.test :refer-macros [deftest is testing run-tests]]           
            ))

;(defspec sort-idempotent-prop
  ;(prop/for-all 
    ;[v (gen/vector gen/int)]
    ;(not= (sort v) (sort (sort v)))))

;(print (tc/quick-check 1000 sort-idempotent-prop))
;(run-tests)
;(run-tests! [sort-idempotent-prop])


(def success 0)

(defn ^:export run []
  (.log js/console "Example test started.")
  (hello/run)
  success)

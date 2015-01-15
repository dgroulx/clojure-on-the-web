(ns organization.views.selmer
  (:require [selmer.parser :refer :all]
            [selmer.filters :refer :all]))

;; Custom selmer filters
(add-filter! :embiginate clojure.string/upper-case)

(defn view [params]
  (render-file "templates/selmer.html" params))



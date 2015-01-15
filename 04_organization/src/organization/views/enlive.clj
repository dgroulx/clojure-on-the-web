(ns organization.views.enlive
  (:require [net.cgrand.enlive-html :refer :all]))

(defsnippet name-list-item "templates/enlive.html" [:ol#names [:li (nth-of-type 1)]] [name]
  [:li] (content name))

(deftemplate main-template "templates/enlive.html" [name-list]
  [:ol#names] (content (map name-list-item name-list)))

(defn list-names [names]
  (main-template names))



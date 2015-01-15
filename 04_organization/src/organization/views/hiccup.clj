(ns organization.views.hiccup
  (:require [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(defn layout [content]
  (html5 [:head
               (include-css "/app.css")
               [:title "Hiccup Templates"]]
              [:body
               [:h1 "I am the Hiccup Layout"]
               content]))

(defn content [names]
  [:div
   [:p "I am the hiccup content"]
   [:p {:class "green"} "I am hiccup with an attribute"]
   [:p.red "I am hiccup with haml style class declaration"]
   (into [:ol] (map (fn [name] [:li name]) names))])

(defn list-names [names]
  (layout (content names)))

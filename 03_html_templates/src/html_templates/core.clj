(ns html-templates.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :refer [wrap-resource]]
            [compojure.core :refer :all]
            [selmer.parser :as selmer]
            [selmer.filters :as filters]
            [hiccup.core :refer :all]
            [hiccup.page :as page]
            [net.cgrand.enlive-html :as enlive]))


(def names ["Alice" "Bob" "Chris"])

;; Custom selmer filters
(filters/add-filter! :embiginate clojure.string/upper-case)


;; Hiccup
(defn hiccup-layout [content]
  (page/html5 [:head
               (page/include-css "/app.css")
               [:title "Hiccup Templates"]]
              [:body
               [:h1 "I am the Hiccup Layout"]
               content]))

(defn hiccup-content [names]
  [:div
   [:p "I am the hiccup content"]
   [:p {:class "green"} "I am hiccup with an attribute"]
   [:p.red "I am hiccup with haml style class declaration"]
   (into [:ol] (map (fn [name] [:li name]) names))])


;; Enlive
(enlive/defsnippet name-list-item "templates/enlive.html" [:ol#names [:li (enlive/nth-of-type 1)]] [name]
  [:li] (enlive/content name))
(enlive/deftemplate main-template "templates/enlive.html" [name-list]
  [:ol#names] (enlive/content (map name-list-item name-list)))


(defroutes handler
  (GET "/selmer/:param" [param] (selmer/render-file "templates/selmer.html" {:param param
                                                                             :my-array [3 5 2 1]}))
  (GET "/hiccup/:param" [param] (hiccup-layout (hiccup-content names)))
  (GET "/enlive/:param" [param] (main-template names)))

(def app (wrap-resource handler "public"))

(defn -main []
  (jetty/run-jetty app {:port 3000
                        :join? false}))

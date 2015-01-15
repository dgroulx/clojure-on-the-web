(defproject liberation "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring "1.3.2"]
                 [ring-middleware-format "0.4.0"]
                 [compojure "1.3.1"]
                 [cheshire "5.4.0"]
                 [korma "0.3.0"]
                 [postgresql "9.3-1102.jdbc4"]
                 [liberator "0.12.2"]]
  :plugins [[lein-ring "0.9.1"]]

  :ring {:handler liberation.core/app})

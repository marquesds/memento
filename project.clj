(defproject memento "0.1.0-SNAPSHOT"
  :description "Memento is a distributed in memory key/value storage (for study purposes)"
  :url "https://github.com/marquesds/memento"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.5.1"]
                 [org.clojure/data.json "2.4.0"]
                 [prevayler-clj/prevayler4 "2020.11.14"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler memento.main/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})

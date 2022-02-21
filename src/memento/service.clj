(ns memento.service
  (:require [memento.state :refer :all]
            [clojure.data.json :as json]
            [compojure.core :refer :all]))

(defn to-json
  ([]
   (json/write-str @state))
  ([body]
   (json/write-str body)))

(defn get-state
  []
  (to-json))

(defn get-value
  [key]
  (to-json {:value (retrieve (keyword key))}))

(defn set-state
  [body]
  (doall
   (map #(create-or-update (keyword %) (body %)) (keys body))))

(defn delete-value
  [key]
  (delete (keyword key)))

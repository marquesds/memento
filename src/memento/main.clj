(ns memento.main
  (:require [memento.http.api-v1 :refer :all]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))

(ns memento.http.api-v1
  (:require [memento.service :refer :all]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(def headers {"Content-Type" "application/json"})

(defroutes app-routes
  (GET "/api/v1/state"
    []
    {:headers headers}
    {:status 200 :body (get-state)})
  (GET "/api/v1/state/:key"
    [key]
    {:headers headers}
    {:status 200 :body (get-value key)})
  (POST "/api/v1/state/"
    {body :body}
    {:headers headers}
    (set-state body)
    {:status 201})
  (PUT "/api/v1/state/"
    {body :body}
    {:headers headers}
    (set-state body)
    {:status 202})
  (DELETE "/api/v1/state/:key"
    [key]
    {:headers headers}
    (delete-value key)
    {:status 202})
  (route/not-found "Not Found"))

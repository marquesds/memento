(ns memento.service
  (:require [memento.state :refer :all]
            [clojure.data.json :as json]
            [compojure.core :refer :all]
            [prevayler-clj.prevayler4 :refer [prevayler! handle!]]))

(defn- handle-state!
  [event business-fn]
  (with-open [p (prevayler! {:initial-state @initial-state
                             :business-fn business-fn})]
    (handle! p event)))

(defn to-json
  ([]
   (json/write-str @initial-state))
  ([body]
   (json/write-str body)))

(defn get-state
  []
  (->
   (prevayler! {:initial-state {}
                 :business-fn (fn [_state event _timestamp] event)})
   (deref)
   (to-json)))

(defn get-value
  [key]
  (to-json {:value (retrieve initial-state (keyword key))}))

(defn set-state
  [event]
  (handle-state! event (fn [initial-state event _timestamp] (merge @initial-state event))))

(defn delete-value
  [key]
  (delete initial-state (keyword key)))

(defn set-state-prevayler
  [body]
  (with-open [p1 (prevayler! {:business-fn set-state})]
     (handle! p1 body)))

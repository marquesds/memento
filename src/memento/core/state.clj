(ns memento.core.state)

(def state (atom {}))

(defn create-or-update
  [key value]
  (swap! state assoc key value)
  state)

(defn delete
  [key]
  (swap! state dissoc key)
  state)

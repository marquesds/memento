(ns memento.state)

(defn create-or-update
  [state key value]
  (swap! state assoc key value)
  state)

(defn delete
  [state key]
  (swap! state dissoc key)
  state)

(defn retrieve
  [state key]
  (key @state))

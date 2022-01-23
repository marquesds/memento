(ns memento.core.state-test
  (:require [clojure.test :refer :all]
            [memento.core.state :refer :all]))

(defn reset-state
  [test-fn]
  (reset! state {})
  test-fn)

(use-fixtures :each reset-state)

(deftest test-create-value-empty-state
  (testing "should create value for an empty state"
    (create-or-update :name "Lucas")
    (is (= @state {:name "Lucas"}))))

(deftest test-create-new-value-existing-state
  (testing "should create new value for an already existing state"
    (create-or-update :name "Lucas")
    (create-or-update :age 31)
    (is (= @state {:name "Lucas" :age 31}))))

(deftest test-create-same-value-existing-state
  (testing "shouldn't create same value for an already existing state"
    (create-or-update :name "Lucas")
    (create-or-update :name "Lucas")
    (is (= @state {:name "Lucas"}))))

(deftest test-update-state-value
  (testing "should update a state value"
    (create-or-update :name "Lucas")
    (create-or-update :name "José")
    (is (= @state {:name "José"}))))

(deftest test-delete-state-value
  (testing "should delete an existing value by a given key"
    (create-or-update :name "Lucas")
    (create-or-update :age 31)
    (delete :age)
    (is (= @state {:name "José"}))))

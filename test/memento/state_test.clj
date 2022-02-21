(ns memento.state-test
  (:require [clojure.test :refer :all]
            [memento.state :refer :all]))

(defn reset-state
  [fn]
  (reset! state {})
  (fn))

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
    (is (= @state {:name "Lucas"}))))

(deftest test-delete-unexistent-state-value
  (testing "shouldn't delete an unexistent value by a given key"
    (delete :age)
    (is (= @state {}))))

(deftest test-retrieve-state-value
  (testing "should retrieve an existing value by a given key"
    (create-or-update :name "Lucas")
    (is (= (retrieve :name) "Lucas"))))

(deftest test-retrieve-unexistent-state-value
  (testing "shouldn't retrieve an unexistent value by a given key"
    (is (= (retrieve :name) nil))))

(ns memento.handler-test
  (:require [clojure.test :refer :all]
            [clojure.data.json :as json]
            [ring.mock.request :as mock]
            [memento.main :refer :all]
            [memento.service :refer :all]
            [memento.state :refer :all]))

(defn reset-state
  [fn]
  (reset! state {})
  (fn))

(use-fixtures :each reset-state)

(deftest test-get-all-states
  (testing "should get all states"
    (set-state {:name "Lucas" :age 31})
    (let [response (app (mock/request :get "/api/v1/state"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"name\":\"Lucas\",\"age\":31}")))))

(deftest test-get-nonexisting-state
  (testing "should return empty hash when the application has no state"
    (let [response (app (mock/request :get "/api/v1/state"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{}")))))

(deftest test-get-state-key
  (testing "should get a specific state value given an existing key"
    (set-state {:name "Lucas" :age 31})
    (let [response (app (mock/request :get "/api/v1/state/age"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"value\":31}")))))

(deftest test-get-nonexisting-key
  (testing "should return a null value when a key doesn't exists"
    (let [response (app (mock/request :get "/api/v1/state/mundial-palmeiras"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"value\":null}")))))

(deftest test-not-found-route
  (testing "should return a 404 status code for an invalid route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

(deftest test-set-state
  (testing "should set a new state with multiple keys/values"
    (let [response (app (mock/request :post "/api/v1/state" (json/write-str {:name "Lucas" :age 31})))]
    (is (= (:status response) 201))
    (is (= (get-state) "{\"name\":\"Lucas\",\"age\":31}")))))

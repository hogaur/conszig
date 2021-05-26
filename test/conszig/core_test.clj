(ns conszig.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [conszig.core :as core]))


(defn serialize
  "Serializes value, returns a byte array"
  [v]
  (let [buff (java.io.ByteArrayOutputStream. 1024)]
    (with-open [dos (java.io.ObjectOutputStream. buff)]
      (.writeObject dos v))
    (.toByteArray buff)))


(deftest test-wrap-middleware-fn
  (testing "wrap-middleware-fn handler-fn deserializes messages before it calls main-fn"
    (let [deserialised-message  "message"
          serialised-message (serialize deserialised-message)]
      (with-redefs [core/main-fn (fn [message] (is (= deserialised-message message) ) deserialised-message )]
        (is (= deserialised-message ((core/wrap-middleware-fn core/main-fn :default) serialised-message)))
        ))))

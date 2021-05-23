(ns conszig.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [conszig.core :as SUT]))

(deftest produces-abc-to-kafka
  (is (= 2 2)))

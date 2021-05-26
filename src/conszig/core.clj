(ns conszig.core
  (:require [ziggurat.init :as ziggurat]
            [ziggurat.middleware.default :as middleware]
            [mount.core :as mount]))

(defn main-fn
  [message]
  (println message)
  :success)

(defn deserialize
  "Accepts a byte array, returns deserialized value"
  [serialized-message]
  (String. serialized-message))

(defn wrap-middleware-fn
    [mapper-fn stream-id]
    (fn [message]
      (let [deserialised-message (deserialize message)]
      (mapper-fn deserialised-message))))

(def handler-fn
  (-> main-fn
      (wrap-middleware-fn :default)))

(defn -main []
  (ziggurat/main {:start-fn #()
                  :stop-fn #()
                  :stream-routes {:default {:handler-fn handler-fn}}}))

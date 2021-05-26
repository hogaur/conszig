(ns conszig.core
  (:require [ziggurat.init :as ziggurat]
            [ziggurat.middleware.default :as middleware]
            [mount.core :as mount]))

(defn start-fn []
  )

(defn stop-fn []
  )

(defn main-fn
  [message]
  (println message)
  (println "ye yaha kaise aaya")
  :success)

(defn deserialize
  "Accepts a byte array, returns deserialized value"
  [bytes]
  (with-open [dis (java.io.ObjectInputStream.
                   (java.io.ByteArrayInputStream. bytes))]
    (.readObject dis)))

(defn wrap-middleware-fn
    [mapper-fn stream-id]
    (fn [message]
      (let [deserialised-message (deserialize message)]
      (println "processing message for stream: " stream-id)
      (println "the message coming in here is: " message)
      (println "the deserialized message in here is: " deserialised-message)
      (mapper-fn deserialised-message))))

(def handler-fn
  (-> main-fn
      (wrap-middleware-fn :default)))

(defn -main []
  (ziggurat/main {:start-fn start-fn
                  :stop-fn stop-fn
                  :stream-routes {:default {:handler-fn handler-fn}}}))

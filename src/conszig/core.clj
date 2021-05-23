(ns conszig.core
  (:require [ziggurat.init :as ziggurat]
            [ziggurat.middleware.default :as middleware]
            [mount.core :as mount]))

(defn start-fn []
  )

(defn stop-fn []
  )

(defn main-fn [message]
  (println message)
  :success)

(defn wrap-middleware-fn
    [mapper-fn stream-id]
    (fn [message]
      (println "processing message for stream: " stream-id)
      (println "the message coming in here is: " message)
      (mapper-fn message)))

(def handler-fn
  (-> main-fn
      (wrap-middleware-fn :default)))

(defn -main []
  (ziggurat/main {:start-fn start-fn
                  :stop-fn stop-fn
                  :stream-routes {:default {:handler-fn handler-fn}}}))

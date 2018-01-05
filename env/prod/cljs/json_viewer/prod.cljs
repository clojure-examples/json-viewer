(ns json-viewer.prod
  (:require [json-viewer.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)

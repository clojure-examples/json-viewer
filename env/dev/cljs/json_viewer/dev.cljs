(ns ^:figwheel-no-load json-viewer.dev
  (:require
    [json-viewer.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)

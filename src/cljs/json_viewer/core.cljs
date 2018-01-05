(ns json-viewer.core
  (:require [reagent.core :as reagent :refer [atom]]
            [ajax.core :refer [GET]]
            [json-html.core :refer [json->hiccup]]))


(defn my-json-loader [_]
  (let [data (reagent/atom nil)]
		(fn [url]
			(if-some [d @data]
        [:div
         [:button {:style {:width "100%"}
                   :on-click #(reset! data nil)}
          "CLEAR JSON"]
         [json->hiccup d]]
        [:button {:style {:width "100%"}
                  :on-click #(GET url {:handler (partial reset! data)})}
         "load json from: " url]))))

(defn my-element [default-url]
	(let [url (reagent/atom default-url)]
		(fn [_]
      [:div
       [:input {:type "text"
                :value @url
                :style {:width "100%"
                        :box-sizing "border-box"}
                :on-change (fn [event]
                             (reset! url (.. event
                                             -target
                                             -value)))}]
       (when-let [url (and (string? @url)
                           (not-empty @url))]
         [my-json-loader url])])))

(defn my-app []
  [:div
   [my-element "https://jsonplaceholder.typicode.com/posts"]])
;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [my-app] (.getElementById js/document "app")))

(defn init! []
  (mount-root))

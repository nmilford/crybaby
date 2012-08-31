(ns crybaby.pages
  (:use [crybaby.config])
  (:use [crybaby.util])
  (:use [crybaby.db])
  (:use [noir.core]))

(defpage "/" [] "curl -X POST 'http://localhost:" crybaby-port "/event?desc=your+event+description+here'")

(defpage [:post "/event"] {:keys [desc]}
  (write-event desc)
  (str desc))

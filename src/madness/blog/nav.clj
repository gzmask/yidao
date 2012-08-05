(ns madness.blog.nav

  ^{:author "Gergely Nagy <algernon@madhouse-project.org>"
    :copyright "Copyright (C) 2012 Gergely Nagy <algernon@madhouse-project.org>"
    :license {:name "GNU General Public License - v3"
              :url "http://www.gnu.org/licenses/gpl.txt"}}
  
  (:require [net.cgrand.enlive-html :as h]
            [madness.config :as cfg]
            [madness.utils :as utils]))

(h/defsnippet recent-item (cfg/template) [:#nav-recent-posts :ul :li]
  [title url]

  [:a] (utils/rewrite-link url title))

(h/defsnippet tag-item (cfg/template) [:#nav-tags :ul :li]
  [tag]

  [:a] (utils/rewrite-link (utils/tag-to-url tag) tag))

(defn recent-posts
  [all-posts]

  (h/clone-for [post (take (cfg/recent-posts :total) all-posts)]
               (h/substitute (recent-item (:title post) (:url post)))))

(defn all-tags
  [all-posts]

  (h/clone-for [tag (utils/tags all-posts)]
               (h/substitute (tag-item tag))))

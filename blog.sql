--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: blogs; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE blogs (
    id integer NOT NULL,
    author character varying,
    title character varying,
    info character varying,
    comment_counter integer
);


ALTER TABLE blogs OWNER TO "Guest";

--
-- Name: blogs_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE blogs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE blogs_id_seq OWNER TO "Guest";

--
-- Name: blogs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE blogs_id_seq OWNED BY blogs.id;


--
-- Name: blogs_tags; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE blogs_tags (
    id integer NOT NULL,
    tag_id integer,
    blog_id integer
);


ALTER TABLE blogs_tags OWNER TO "Guest";

--
-- Name: blogs_tags_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE blogs_tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE blogs_tags_id_seq OWNER TO "Guest";

--
-- Name: blogs_tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE blogs_tags_id_seq OWNED BY blogs_tags.id;


--
-- Name: comments; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE comments (
    id integer NOT NULL,
    name character varying,
    comment character varying,
    blog_id integer
);


ALTER TABLE comments OWNER TO "Guest";

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE comments_id_seq OWNER TO "Guest";

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE comments_id_seq OWNED BY comments.id;


--
-- Name: tags; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE tags (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE tags OWNER TO "Guest";

--
-- Name: tags_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tags_id_seq OWNER TO "Guest";

--
-- Name: tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE tags_id_seq OWNED BY tags.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY blogs ALTER COLUMN id SET DEFAULT nextval('blogs_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY blogs_tags ALTER COLUMN id SET DEFAULT nextval('blogs_tags_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY comments ALTER COLUMN id SET DEFAULT nextval('comments_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY tags ALTER COLUMN id SET DEFAULT nextval('tags_id_seq'::regclass);


--
-- Data for Name: blogs; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY blogs (id, author, title, info, comment_counter) FROM stdin;
\.


--
-- Name: blogs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('blogs_id_seq', 2, true);


--
-- Data for Name: blogs_tags; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY blogs_tags (id, tag_id, blog_id) FROM stdin;
\.


--
-- Name: blogs_tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('blogs_tags_id_seq', 1, true);


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY comments (id, name, comment, blog_id) FROM stdin;
\.


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('comments_id_seq', 1, false);


--
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY tags (id, name) FROM stdin;
\.


--
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('tags_id_seq', 1, true);


--
-- Name: blogs_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY blogs
    ADD CONSTRAINT blogs_pkey PRIMARY KEY (id);


--
-- Name: blogs_tags_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY blogs_tags
    ADD CONSTRAINT blogs_tags_pkey PRIMARY KEY (id);


--
-- Name: comments_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: tags_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: Guest
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM "Guest";
GRANT ALL ON SCHEMA public TO "Guest";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


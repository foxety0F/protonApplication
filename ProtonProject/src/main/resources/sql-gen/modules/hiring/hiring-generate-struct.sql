CREATE TABLE proton_hired_config
(
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    user_phone character varying(80) COLLATE pg_catalog."default",
    about text COLLATE pg_catalog."default",
    CONSTRAINT proton_hired_config_pkey PRIMARY KEY (id)
);

CREATE TABLE proton_hired_experience
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 100 MAXVALUE 9999999999999 CACHE 1 ),
    brief_id bigint,
    title_name character varying(255) COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    skill_points text COLLATE pg_catalog."default",
    start_date date,
    end_date date,
    is_current numeric(2,0),
    order_num numeric(10,0),
    CONSTRAINT proton_hired_experience_pkey PRIMARY KEY (id),
    CONSTRAINT proton_hired_experience_fk FOREIGN KEY (brief_id)
        REFERENCES proton_hired_config (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE proton_hired_skills
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 100 MAXVALUE 9999999999999 CACHE 1 ),
    skill_name character varying(120) COLLATE pg_catalog."default",
    skill_description character varying(500) COLLATE pg_catalog."default",
    skill_min_scale numeric(10,0) DEFAULT 0,
    skill_max_scale numeric(10,0) DEFAULT 10,
    CONSTRAINT proton_hired_skils_pkey PRIMARY KEY (id)
);

CREATE TABLE proton_hired_user_skills
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 100 MAXVALUE 9999999999999 CACHE 1 ),
    brief_id bigint NOT NULL,
    value bigint,
    purpose text COLLATE pg_catalog."default",
    rated_emp bigint,
    skill_id bigint,
    CONSTRAINT proton_hired_user_skills_pkey PRIMARY KEY (id),
    CONSTRAINT proton_hired_user_skills_fk FOREIGN KEY (brief_id)
        REFERENCES proton_hired_config (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT proton_hired_user_skills_fk2 FOREIGN KEY (rated_emp)
        REFERENCES app_user (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT proton_hired_user_skills_fk3 FOREIGN KEY (skill_id)
        REFERENCES proton_hired_skills (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE SEQUENCE "PROTON_HIRED_CONFIG_SEQ"
    INCREMENT 1
    START 100
    MINVALUE 100
    MAXVALUE 99999999999999
    CACHE 1;

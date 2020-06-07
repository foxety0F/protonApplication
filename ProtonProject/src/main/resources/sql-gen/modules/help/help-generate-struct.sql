CREATE TABLE proton_help_types
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 100 MAXVALUE 9999999999999 CACHE 1 ),
    type_name character varying(120) COLLATE pg_catalog."default",
    type_description character varying(250) COLLATE pg_catalog."default",
    CONSTRAINT proton_help_types_pkey PRIMARY KEY (id)
);

CREATE TABLE proton_help_config
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 101 MINVALUE 101 MAXVALUE 9999999999999 CACHE 1 ),
    help_name character varying(120) COLLATE pg_catalog."default" NOT NULL,
    help_description character varying(800) COLLATE pg_catalog."default",
    request_url character varying(500) COLLATE pg_catalog."default",
    ref_type_id bigint,
    help_text text COLLATE pg_catalog."default",
    CONSTRAINT proton_help_config_pkey PRIMARY KEY (id),
    CONSTRAINT "HELP_TYPE_FK" FOREIGN KEY (ref_type_id)
        REFERENCES proton_help_types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
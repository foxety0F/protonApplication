/*Create core proton modules table*/
CREATE TABLE proton_modules(
    id smallint NOT NULL ,
    isactive smallint,
    CONSTRAINT proton_modules_pk PRIMARY KEY (id)
);

/*Create support table for information*/
CREATE TABLE proton_modules_info(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    id_module bigint,
    url character varying(250) COLLATE pg_catalog."default",
    road character varying(250) COLLATE pg_catalog."default",
    description character varying(3000) COLLATE pg_catalog."default",
    CONSTRAINT proton_modules_info_pk PRIMARY KEY (id),
    CONSTRAINT proton_modules_info_fk FOREIGN KEY (id_module)
        REFERENCES proton_modules (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

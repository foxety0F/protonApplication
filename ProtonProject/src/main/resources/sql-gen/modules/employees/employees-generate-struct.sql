CREATE TABLE proton_employees
(
    id_employee bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    login character varying(120) COLLATE pg_catalog."default",
    isactive smallint,
    id_user bigint,
    sys_add_tst timestamp without time zone,
    CONSTRAINT proton_employees_pk PRIMARY KEY (id_employee)
);

CREATE TABLE proton_employee_titles
(
    id_title bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name_title character varying(120) COLLATE pg_catalog."default",
    description character varying(400) COLLATE pg_catalog."default",
    CONSTRAINT proton_employee_titles_pk PRIMARY KEY (id_title)
);

CREATE TABLE proton_employees_groups
(
    id_group bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name_group character varying(120) COLLATE pg_catalog."default",
    description character varying(400) COLLATE pg_catalog."default",
    CONSTRAINT proton_employees_groups_pk PRIMARY KEY (id_group)
);

CREATE TABLE proton_employees_attr
(
    id_row bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 100 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    id_employee bigint,
    beg_date date,
    end_date date,
    id_group bigint,
    id_title bigint,
    pc_number character varying(120) COLLATE pg_catalog."default",
    ip_address character varying(120) COLLATE pg_catalog."default",
    place character varying(120) COLLATE pg_catalog."default",
    lastrow integer,
    CONSTRAINT proton_employees_attr_pk PRIMARY KEY (id_row),
    CONSTRAINT proton_employees_attr_fk FOREIGN KEY (id_employee)
        REFERENCES public.proton_employees (id_employee) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT proton_employees_attr_fk2 FOREIGN KEY (id_group)
        REFERENCES public.proton_employees_groups (id_group) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT proton_employees_attr_fk3 FOREIGN KEY (id_title)
        REFERENCES public.proton_employee_titles (id_title) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
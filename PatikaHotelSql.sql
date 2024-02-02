PGDMP      %                |            patikahotel    16.1    16.1 (    9           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            :           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ;           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            <           1262    16769    patikahotel    DATABASE     m   CREATE DATABASE patikahotel WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE patikahotel;
                postgres    false            �            1259    16779    hotel    TABLE     �  CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name text NOT NULL,
    hotel_address text NOT NULL,
    hotel_email text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    hotel_park boolean NOT NULL,
    hotel_wifi boolean NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_gym boolean NOT NULL,
    hotel_consierge boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16778    hotel_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hotel_hotel_id_seq;
       public          postgres    false    218            =           0    0    hotel_hotel_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.hotel_hotel_id_seq OWNED BY public.hotel.hotel_id;
          public          postgres    false    217            �            1259    16806    hotel_hotel_id_seq1    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_hotel_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    16788    pension    TABLE     �   CREATE TABLE public.pension (
    pension_id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_type text NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    16787    pension_pension_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pension_pension_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.pension_pension_id_seq;
       public          postgres    false    220            >           0    0    pension_pension_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.pension_pension_id_seq OWNED BY public.pension.pension_id;
          public          postgres    false    219            �            1259    16805    pension_pension_id_seq1    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    16816    reservation    TABLE     f  CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    room_id integer NOT NULL,
    guest_name text NOT NULL,
    guest_tcno text NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    guest_num integer NOT NULL,
    total_price integer NOT NULL,
    guest_email text NOT NULL,
    guest_phone text NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16815    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    228            �            1259    16808    room    TABLE     
  CREATE TABLE public.room (
    room_id integer NOT NULL,
    hotel_id integer NOT NULL,
    season_id integer NOT NULL,
    pension_id integer NOT NULL,
    room_type text NOT NULL,
    room_stock integer NOT NULL,
    price_adult integer NOT NULL,
    price_child integer NOT NULL,
    room_m2 integer NOT NULL,
    room_bed_number integer NOT NULL,
    room_tv boolean NOT NULL,
    room_bar boolean NOT NULL,
    room_console boolean NOT NULL,
    room_cashbox boolean NOT NULL,
    room_projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16807    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    226            �            1259    16797    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    16796    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222            �            1259    16771    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16770    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            ,          0    16779    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_email, hotel_phone, hotel_star, hotel_park, hotel_wifi, hotel_pool, hotel_gym, hotel_consierge, hotel_spa, hotel_room_service) FROM stdin;
    public          postgres    false    218   -/       .          0    16788    pension 
   TABLE DATA           E   COPY public.pension (pension_id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    220   �/       6          0    16816    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, guest_name, guest_tcno, check_in_date, check_out_date, guest_num, total_price, guest_email, guest_phone) FROM stdin;
    public          postgres    false    228   ]0       4          0    16808    room 
   TABLE DATA           �   COPY public.room (room_id, hotel_id, season_id, pension_id, room_type, room_stock, price_adult, price_child, room_m2, room_bed_number, room_tv, room_bar, room_console, room_cashbox, room_projection) FROM stdin;
    public          postgres    false    226   (1       0          0    16797    season 
   TABLE DATA           N   COPY public.season (season_id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    222   �1       *          0    16771    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    216   Z2       ?           0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 1, false);
          public          postgres    false    217            @           0    0    hotel_hotel_id_seq1    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq1', 4, true);
          public          postgres    false    224            A           0    0    pension_pension_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.pension_pension_id_seq', 1, false);
          public          postgres    false    219            B           0    0    pension_pension_id_seq1    SEQUENCE SET     F   SELECT pg_catalog.setval('public.pension_pension_id_seq1', 17, true);
          public          postgres    false    223            C           0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 12, true);
          public          postgres    false    227            D           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 20, true);
          public          postgres    false    225            E           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 21, true);
          public          postgres    false    221            F           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 8, true);
          public          postgres    false    215            �           2606    16786    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    218            �           2606    16795    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    220            �           2606    16822    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    228            �           2606    16812    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    226            �           2606    16801    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    222            �           2606    16777    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    216            ,   �   x�U��
1��ه9���+�Y�ڬ?����\l|*�sIRDf؏�����l������?#�0�$b��ʪ�i�);�J{��� -0(�-�8K����5���s�%�#{)��;��{Mbj�u�e"�(tCr      .   �   x�m�K�0D��a��nI���EBB��o�YA*�~3� f�i�ć�$Q�Ya3����k&��'�{���Y�S�tTVtGcQ�>�(	���]c�$~��/�Z+�����8|I�Ob���G#���"���=�      6   �   x�u�A�0���\ �L[����iL#��&���g�z/1�Y��ϛ�PBeMT��֩; q��x�R���J@�4(kʳS�&'���C��~�h���A��;�/J>)<��2^�.?��L
�aI�t�V�NC��Uhv.![��`�og&�	p�p��ث����$5S��3)8;&��PGd      4   �   x�]��
1E׷#I_�n�AFD�qv���L��"��Г�i83�b]n���LS��w9;v�؃.�u[���Q�$��UR�)�e���%8���T&Zt��D
��� K����qs���/\�=�S���)����z��4:�X�ce��M���K���(۶�����ĉBz      0   _   x�e�K
�@Cד��4�|�.��q*"t��^h�J7�j�<k�A�'�h7�ْ�,vp&R��^<[ni��G"�uK!��?�Z!��ٳ�sp&�%      *   P   x�3�LL���3�440�0��8Ssr�+SS9���0�)D�P�!T��6
A�������-�Lc���=... ��'�     
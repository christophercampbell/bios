CREATE TABLE `rank` (
id integer AUTO_INCREMENT NOT NULL,
name varchar (16) NOT NULL,
PRIMARY KEY(id)
);

-- taxa ranks
INSERT INTO `rank` VALUES ( 1,  'Unranked' );
INSERT INTO `rank` VALUES ( 2,  'Domain' );
INSERT INTO `rank` VALUES ( 3,  'Kingdom' );
INSERT INTO `rank` VALUES ( 4,  'Phylum' );
INSERT INTO `rank` VALUES ( 5,  'Class' );
INSERT INTO `rank` VALUES ( 6,  'Order' );
INSERT INTO `rank` VALUES ( 7,  'Family' );
INSERT INTO `rank` VALUES ( 8,  'Genus' );
INSERT INTO `rank` VALUES ( 9,  'Species' );
INSERT INTO `rank` VALUES ( 10, 'Variation' );
INSERT INTO `rank` VALUES ( 11, 'Subspecies' );

CREATE TABLE `taxon` (
id integer AUTO_INCREMENT NOT NULL,
rank_id integer NOT NULL,
parent_id integer,
name varchar (128) NOT NULL,
common_name varchar (128),
PRIMARY KEY(id),
CONSTRAINT FOREIGN KEY (rank_id) references `rank` (id),
CONSTRAINT FOREIGN KEY (parent_id) references `taxon` (id)
);

-- some fixed taxa to start with

-- Domain
INSERT INTO `taxon` VALUES ( 1, 2, NULL, 'Eukaryota', 'Eukaryotes' );
INSERT INTO `taxon` VALUES ( 2, 2, NULL, 'Bacteria', 'Bacteria' );
INSERT INTO `taxon` VALUES ( 3, 2, NULL, 'Archaea', 'Archaea' );

-- Kingdom
INSERT INTO `taxon` VALUES ( 10, 3, 1, 'Plantae', 'Plants' );
INSERT INTO `taxon` VALUES ( 11, 3, 1, 'Fungi', 'Fungus' );
INSERT INTO `taxon` VALUES ( 12, 3, 1, 'Animalia', 'Animals' );

-- Phylum
INSERT INTO `taxon` VALUES ( 100, 4, 10, 'Bryophyta', 'Mosses' );
INSERT INTO `taxon` VALUES ( 101, 4, 10, 'Marchantiophyta', 'Liverworts' );
INSERT INTO `taxon` VALUES ( 102, 4, 10, 'Pteridophyta', 'Ferns & horsetails' );
INSERT INTO `taxon` VALUES ( 103, 4, 10, 'Gymnosperms', 'Gynkgos' );
INSERT INTO `taxon` VALUES ( 104, 4, 10, 'Angiosperms', 'Flowering plants' );
INSERT INTO `taxon` VALUES ( 105, 4, 10, 'Pinophyta', 'Conifers' );


-- Some starting examples: Order(6), Family(7), Genus(8), species(9)

-- Miner's lettuce
INSERT INTO `taxon` VALUES ( 500, 5, 104, 'Unknown class', 'Unkown class' );
INSERT INTO `taxon` VALUES ( 1000, 6, 500,  'Caryophyllales', '' );
INSERT INTO `taxon` VALUES ( 1001, 7, 1000, 'Montiaceae', '' );
INSERT INTO `taxon` VALUES ( 1002, 8, 1001, 'Claytonia', '' );
INSERT INTO `taxon` VALUES ( 1003, 9, 1002, 'perfoliata', 'Miner''s lettuce' );

-- Tall flat sedge
INSERT INTO `taxon` VALUES ( 501, 5, 104, 'Unknown class', 'Unkown class' );
INSERT INTO `taxon` VALUES ( 1004, 6, 501,  'Poales', 'Grasses' );
INSERT INTO `taxon` VALUES ( 1005, 7, 1004, 'Cyperaceae', 'Sedges' );
INSERT INTO `taxon` VALUES ( 1006, 8, 1005, 'Cyperus', 'Flat sedges' );
INSERT INTO `taxon` VALUES ( 1007, 9, 1006, 'eragrostis', 'Tall flat sedge' );

-- California bee plant
INSERT INTO `taxon` VALUES ( 502, 5, 104, 'Unknown class', 'Unkown class' );
INSERT INTO `taxon` VALUES ( 1008, 6, 502,  'Lamiales', 'Flowering dicots' );
INSERT INTO `taxon` VALUES ( 1009, 7, 1008, 'Scrophulariaceae', '' );
INSERT INTO `taxon` VALUES ( 1010, 8, 1009, 'Scrophularia', 'Figwort' );
INSERT INTO `taxon` VALUES ( 1011, 9, 1010, 'californica', 'California bee plant' );

-- Redwood
INSERT INTO `taxon` VALUES ( 503, 5, 104, 'Unknown class', 'Unkown class' );
INSERT INTO `taxon` VALUES ( 1012, 6, 503,  'Pinales', 'Pines' );
INSERT INTO `taxon` VALUES ( 1013, 7, 1012, 'Cupressaceae', 'Cypress' );
INSERT INTO `taxon` VALUES ( 1014, 8, 1013, 'Sequoai', 'Redwood' );
INSERT INTO `taxon` VALUES ( 1015, 9, 1014, 'sempervirens', 'California redwood' );

-- giant horsetail, Equisetum telmateia ssp. braunii
INSERT INTO `taxon` VALUES ( 1016, 5, 102,   'Equisetopsida', '' ); -- class!!
INSERT INTO `taxon` VALUES ( 1017, 6, 1016,  'Equisetales', '' );
INSERT INTO `taxon` VALUES ( 1018, 7, 1017,  'Equisetaceae', '' );
INSERT INTO `taxon` VALUES ( 1019, 8, 1018,  'Equisetum', '' );
INSERT INTO `taxon` VALUES ( 1020, 9, 1019,  'telmateia', 'Giant horsetail' );
INSERT INTO `taxon` VALUES ( 1021, 11, 1020, 'braunii', 'Northern Giant horsetail' );


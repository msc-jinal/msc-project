create table tlb_pathway(
	id varchar(100) NOT NULL primary key,
	name varchar(200) NOT NULL,
	url varchar(200),
	pathway_db_name varchar(30),
	total_gene int,
	total_protein int,
	total_metabolite int,
	total_rna int
);

insert into tlb_pathway(id,name, url,pathway_db_name) values('WP_WP306','Focal Adhesion','http://www.wikipathways.org/index.php/Pathway:WP306','WikiPathways');


create table tlb_pathway_datanode(
	pathway_id varchar(100),
	product_type varchar(100),
	product_name varchar(200),
	gene_id varchar(100),
	datasource varchar(100),
	FOREIGN KEY(pathway_id) REFERENCES tlb_pathway(id)
);
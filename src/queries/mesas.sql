WITH MiembrosMesas AS (
    SELECT mesa.mesaElectoralId,
           mesa.numero AS numero,
           centro.nombre AS centro,
           sector.nombre AS sector,
           municipio.nombre AS municipio,
           departamento.nombre AS departamento,
           (
               SELECT COUNT( * )
                 FROM MiembrosMer miembro
                WHERE miembro.mesaElectoralId = mesa.mesaElectoralId
           )
           AS MiembrosAsignados
      FROM MesasElectorales mesa
           INNER JOIN
           CentrosVotacion centro ON mesa.centroVotacionId = centro.centroVotacionId
           INNER JOIN
           SectoresElectorales sector ON centro.sectorElectoralId = sector.sectorElectoralId
           INNER JOIN
           Municipios municipio ON sector.municipioId = municipio.municipioId
           INNER JOIN
           Departamentos departamento ON departamento.departamentoId = municipio.departamentoId
)
SELECT *
  FROM MiembrosMesas miembroMesa;

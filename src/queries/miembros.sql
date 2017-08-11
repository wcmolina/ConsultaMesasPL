SELECT miembro.*,
       lugar.nombre,
       mesa.numero
  FROM MiembrosMer miembro
       INNER JOIN
       LugaresPoblados lugar ON miembro.lugarPobladoId = lugar.lugarPobladoId
       LEFT JOIN
       MesasElectorales mesa ON miembro.mesaElectoralId = mesa.mesaElectoralId;

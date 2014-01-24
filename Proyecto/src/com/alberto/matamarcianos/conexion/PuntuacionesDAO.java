package com.alberto.matamarcianos.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

public class PuntuacionesDAO {
	
	public Collection<PuntuacionesDTO> obtenerPuntuaciones() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			con = MysqlConexion.obtenerConexion();
			String sql = "SELECT jugador, puntuacion, version FROM puntuaciones ORDER BY `puntuacion` DESC";
			
			pstm = con.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			Vector<PuntuacionesDTO> ret = new Vector<PuntuacionesDTO>();
			PuntuacionesDTO puntuaciones = null;
			
			while(rs.next()) {
				puntuaciones = new PuntuacionesDTO();
				puntuaciones.fijarJugador(rs.getString("jugador"));
				puntuaciones.fijarPuntuacion(rs.getInt("puntuacion"));
				puntuaciones.fijarVersion(rs.getString("version"));
				ret.add(puntuaciones);
			}
			
			return ret;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}

}

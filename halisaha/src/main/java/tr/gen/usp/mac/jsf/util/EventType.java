/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.gen.usp.mac.jsf.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sitki.poyraz
 */
    public enum EventType {

        MAC_EKLE(10, "{0} maçı oluşturuldu.", "whistle.png"),
        MAC_GUNCELLE(20, "{0} maçı güncellendi", "whistle.png"),
        MAC_IPTAL(30, "{0} maçı iptal edildi.", "whistle.png"),
        OYUNCU_EKLE(40, "{0} oyuncusu eklendi.", "player.png"),
        OYUNCU_GUNCELLE(50, "{0} oyuncusu güncellendi.", "player.png"),
        OYUNCU_SIL(60, "{0} oyuncu silindi.", "player.png"),
        MACA_OYUNCU_EKLE(70, "{0} maç kadrosuna {1} eklendi.", "player.png"),
        MACTAN_OYUNCU_SIL(80, "{0} maç kadrosundan {1} çıkarıldı.", "player.png"),
        SAHA_EKLE(90, "{0} sahası eklendi.", "pitch.png"),
        SAHA_GUNCELLE(100, "{0} sahası güncellendi.", "pitch.png"),
        SAHA_SIL(110, "{0} sahası silindi.", "pitch.png");

        private final int type;
        private final String message;
        private final String icon;
        private static final Map<Integer, EventType> map = new HashMap<>();

        static {
            for (final EventType en : EventType.values()) {
                map.put(en.getType(), en);
            }
        }

        private EventType(final int type, final String message, String icon) {
            this.type = type;
            this.message = message;
            this.icon = icon;
        }

        public int getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }

        public String getIcon() {
            return icon;
        }
        
        public static EventType getByType(final int type) {
        return map.get(type);
    }
    }

/*
 * Copyright 2012 tono-nakae.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.awt.{ Frame, Graphics, Color }
import java.awt.event.{ WindowEvent, WindowAdapter }
import PLP.{ Plate, Locatable, Box }

case class PLPFrame(b: Locatable, scale: Int=5) extends Frame { 
  override def paint(g: Graphics) = drawops.foreach(_(g))
  val tab = 10
  val drawops = draw(b, tab, tab) ++ Seq(
    (g: Graphics) => g.setColor(Color.black),
    (g: Graphics) => g.drawRect(tab * scale, tab * scale, b.l * scale, b.w * scale))
  object Colors {
    def next: Graphics => Unit = { val x = st.head; st = st.tail; x }
    var st: Stream[Graphics => Unit] = rotation
    lazy val rotation: Stream[Graphics => Unit] =
      ((g: Graphics) => g.setColor(Color.red)) #::
      ((g: Graphics) => g.setColor(Color.blue)) #::
      ((g: Graphics) => g.setColor(Color.magenta)) #::
      ((g: Graphics) => g.setColor(Color.orange)) #::
      ((g: Graphics) => g.setColor(Color.pink)) #::
      ((g: Graphics) => g.setColor(Color.green)) #::
      ((g: Graphics) => g.setColor(Color.cyan)) #:: rotation
  }
  def draw(b: Locatable, x: Int, y: Int, rot: Boolean=false): Seq[(Graphics) => Unit] = b match {
    case Box(name, l, w) if rot =>
      List(Colors.next,
           (g: Graphics) => g.fillRect(x * scale, y * scale, w * scale, l * scale),
           (g: Graphics) => g.setColor(Color.black),
           (g: Graphics) => g.drawString(name.toString, x * scale, y * scale + 10),
           (g: Graphics) => g.drawString(w.toString, (x + w / 2) * scale, y * scale + 10),
           (g: Graphics) => g.drawString(l.toString, x * scale, (y + l / 2) * scale + 10))
    case Box(name, l, w) =>
      List(Colors.next,
           (g: Graphics) => g.fillRect(x * scale, y * scale, l * scale, w * scale),
           (g: Graphics) => g.setColor(Color.black),
           (g: Graphics) => g.drawString(name.toString, x * scale, y * scale + 10),
           (g: Graphics) => g.drawString(l.toString, (x + l / 2) * scale, y * scale + 10),
           (g: Graphics) => g.drawString(w.toString, x * scale, (y + w / 2) * scale + 10))
    case Plate(l, w, bs) if rot =>
      bs.zip{ List(
        (b: Locatable) => draw(b, x, y, rot),
        (b: Locatable) => draw(b, x, y + l - b.w, !rot),
        (b: Locatable) => draw(b, x + w - b.w, y + l - b.l, rot),
        (b: Locatable) => draw(b, x + w - b.l, y, !rot),
        (b: Locatable) => draw(b, x + bs(0).w, y + bs(3).w, rot))
      }.flatMap{ case (b, f) => f(b) }
    case Plate(l, w, bs) =>
      bs.zip{ List(
        (b: Locatable) => draw(b, x, y, rot),
        (b: Locatable) => draw(b, x + l - b.w, y, !rot),
        (b: Locatable) => draw(b, x + l - b.l, y + w - b.w, rot),
        (b: Locatable) => draw(b, x, y + w - b.l, !rot),
        (b: Locatable) => draw(b, x + bs(3).w, y + bs(0).w, rot))
      }.flatMap{ case (b, f) => f(b) }
  }
  addWindowListener(new WindowAdapter() {
    override def windowClosing(e: WindowEvent) { removeNotify() }
  })
  setSize((tab * 2 + b.l) * scale, (tab * 2 + b.w) * scale)
  setVisible(true)
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import lev.LOutFile;
import lev.Ln;

/**
 *
 * @author Justin Swanson
 */
class ModExporter extends LOutFile {
	int written = 0;
    private Mod exportMod;
    private Mod srcMod;
    private MajorRecord srcMajor;

    ModExporter (File path, Mod mod) throws FileNotFoundException {
	super(path);
	exportMod = mod;
    }

    public Mod getExportMod() {
	return exportMod;
    }

    public void setSourceMod (Mod srcMod) {
	this.srcMod = srcMod;
    }

    public Mod getSourceMod () {
	return srcMod;
    }

    public void setSourceMajor(MajorRecord src) {
	srcMajor = src;
    }

    public MajorRecord getSourceMajor() {
	return srcMajor;
    }



	@Override
	public void write(byte b) throws IOException {
		written++;
		super.write(b);
	}

	@Override
	public void write(byte[] array) throws IOException {
		written += array.length;
		super.write(array);
	}

	@Override
	public void write(byte[] array, int size) throws IOException {
		if (array.length > size && size != 0)
				throw new Error(new String(array) + " larger than " + size);

		this.write(array);
		if (size - array.length > 0) {
			this.writeZeros(size - array.length);
		}
	}

	@Override
	public void write(String input) throws IOException {
		this.write(input, 0);
	}

	@Override
	public void write(String input, int size) throws IOException {
		this.write(Ln.toByteArray(input), size);
	}

	@Override
	public void write(boolean input, int size) throws IOException {
		if (input) {
			this.write(1, size);
		} else {
			this.write(0, size);
		}
	}
}

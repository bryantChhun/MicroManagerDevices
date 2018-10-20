package MMInterfaces;

import MMExceptions.CMMException;

import java.util.ArrayList;

public interface MMXYStageInterface {

    void 	setXYPosition (String xyStageLabel, double x, double y) throws CMMException;

    void 	setXYPosition (double x, double y) throws CMMException;

    void 	setRelativeXYPosition (String xyStageLabel, double dx, double dy) throws CMMException;

    void 	setRelativeXYPosition (double dx, double dy) throws CMMException;

    void 	getXYPosition (String xyStageLabel, double x_stage, double y_stage) throws CMMException;

    void 	getXYPosition (double x_stage, double y_stage) throws CMMException;

    double 	getXPosition (String xyStageLabel) throws CMMException;

    double 	getYPosition (String xyStageLabel) throws CMMException;

    double 	getXPosition () throws CMMException;

    double 	getYPosition () throws CMMException;

    void 	stop (String xyOrZStageLabel) throws CMMException;

    void 	home (String xyOrZStageLabel) throws CMMException;

    void 	setOriginXY (String xyStageLabel) throws CMMException;

    void 	setOriginXY () throws CMMException;

    void 	setOriginX (String xyStageLabel) throws CMMException;

    void 	setOriginX () throws CMMException;

    void 	setOriginY (String xyStageLabel) throws CMMException;

    void 	setOriginY () throws CMMException;

    void 	setAdapterOriginXY (String xyStageLabel, double newXUm, double newYUm) throws CMMException;

    void 	setAdapterOriginXY (double newXUm, double newYUm) throws CMMException;

    boolean isXYStageSequenceable (String xyStageLabel) throws CMMException;

    void 	startXYStageSequence (String xyStageLabel) throws CMMException;

    void 	stopXYStageSequence (String xyStageLabel) throws CMMException;

    long 	getXYStageSequenceMaxLength (String xyStageLabel) throws CMMException;

    void 	loadXYStageSequence (String xyStageLabel, ArrayList<Double> xSequence, ArrayList<Double> ySequence) throws CMMException;
}
